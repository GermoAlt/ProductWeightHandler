package cl.assachile.email.service

import cl.assachile.email.parser.Supplier
import com.sun.mail.imap.IdleManager
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.*
import java.util.concurrent.Executors
import javax.mail.*
import javax.mail.event.MessageCountAdapter
import javax.mail.event.MessageCountEvent
import javax.mail.internet.MimeBodyPart


@Service
class EmailService(
    private var excelService: ExcelService,
    @Value("\${assa.mail.host}")
    private var host: String,
    @Value("\${assa.mail.username}")
    private var username: String,
    @Value("\${assa.mail.password}")
    private var password: String

): InitializingBean {

    var messages: Array<Message> = arrayOf()
    var idleManager: IdleManager? = null

    override fun afterPropertiesSet() {
        connectToEmailServer()
    }

    fun connectToEmailServer(){
        val es = Executors.newCachedThreadPool()

        val props = Properties()
        props.setProperty("mail.imap.ssl.enable", "true")
        props["mail.event.scope"] = "application"
        props["mail.event.executor"] = es
        props["mail.imap.usesocketchannels"] = true

        val session = Session.getInstance(props)
        val store = session.getStore("imap")
        store.connect(host, username, password)

        val inbox = store.getFolder("INBOX")
        inbox.open(Folder.READ_ONLY)
        messages = inbox.messages
        idleManager = IdleManager(session, es)
        idleManager!!.watch(inbox)

        inbox.addMessageCountListener(object : MessageCountAdapter() {
            override fun messagesAdded(ev: MessageCountEvent) {
                val folder = ev.source as Folder
                val msgs = ev.messages
                println(
                    "Folder: " + folder +
                            " got " + msgs.size + " new messages"
                )
                if(msgs[0].contentType.contains("multipart")){
                    handleAttachments(msgs[0])
                }
                try {
                    // process new messages
                    idleManager!!.watch(folder) // keep watching for new messages
                } catch (mex: MessagingException) {
                    connectToEmailServer()
                }
            }
        })
    }

    @Throws(IOException::class, MessagingException::class)
    fun handleAttachments(message: Message){
        val multiPart = message.content as Multipart
        val numberOfParts = multiPart.count
        for (partCount in 0 until numberOfParts) {
            val part = multiPart.getBodyPart(partCount) as MimeBodyPart
            if (Part.ATTACHMENT.equals(part.disposition, true) && part.fileName.endsWith(".xlsx")) {
                excelService.parseExcel(part.inputStream, getSupplier(message))
            }
        }
    }

    fun getSupplier(message: Message): Supplier {
        return Supplier.GUARANI
    }
}