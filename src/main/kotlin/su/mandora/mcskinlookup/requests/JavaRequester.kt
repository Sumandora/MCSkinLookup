package su.mandora.mcskinlookup.requests

import java.net.URL

class JavaRequester : Requester {
    override fun request(url: String): String {
        val urlConnection = URL(url).openConnection()
        urlConnection.connect()
        return urlConnection.getInputStream().readAllBytes().decodeToString()
    }
}