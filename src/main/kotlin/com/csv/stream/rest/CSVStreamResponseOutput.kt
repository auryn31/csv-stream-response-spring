package com.csv.stream.rest

import com.csv.stream.dataprovider.DataProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.BufferedWriter
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.concurrent.CountDownLatch

@Component
class CSVStreamResponseOutput : StreamingResponseBody {

    @Autowired
    lateinit var dataProvider: DataProvider

    /**
     * writes every line from the dataprovider to the output
     */
    override fun writeTo(os: OutputStream) {
        val writer = BufferedWriter(OutputStreamWriter(os))
        val countDownLatch = CountDownLatch(1)
        dataProvider.getDataStream().subscribe({
            writer.write(it)
            writer.write("\n")
            writer.flush()
        }, ::println, {
            os.close()
            countDownLatch.countDown()
        })
        countDownLatch.await()
        writer.flush()
    }
}