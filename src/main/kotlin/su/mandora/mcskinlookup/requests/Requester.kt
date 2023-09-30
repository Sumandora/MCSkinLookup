package su.mandora.mcskinlookup.requests

interface Requester {
    /**
     * Takes in a URL and returns the HTTP content returned by the request to said URL
     */
    fun request(url: String): String
}