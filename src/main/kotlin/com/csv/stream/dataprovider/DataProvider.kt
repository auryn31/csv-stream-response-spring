package com.csv.stream.dataprovider

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


@Component
class DataProvider {

    /**
     * create flowable from sampledata.csv
     * @return every 100ms one line
     */
    fun getDataStream(): Flowable<String> {

        val csvFile = this::class.java.getResource("/static/sampledata.csv").openStream()

        return Flowable.using(
                { BufferedReader(InputStreamReader(csvFile)) },
                { reader -> Flowable.fromIterable<String>(getIterableFromIterator(reader.lines().iterator())) },
                { reader -> reader.close() })
    }

    /**
     * convert the iterator to iterable
     */
    private fun <T> getIterableFromIterator(iterator: Iterator<T>): Iterable<T> {
        return object : Iterable<T> {
            override fun iterator(): Iterator<T> {
                return iterator
            }
        }
    }


}