package com.csv.stream.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody


@Controller
class RestEndpoint {

    var streamResponse: CSVStreamResponseOutput

    constructor(csvStreamResponseOutput: CSVStreamResponseOutput) {
        this.streamResponse = csvStreamResponseOutput
    }


    @GetMapping(path = ["stream"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    @CrossOrigin(origins = ["*"])
    fun getCSVAsStreamWithExtraEndpointForLocust(): StreamingResponseBody {
        return streamResponse
    }

}
