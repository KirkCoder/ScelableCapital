package com.example.scelablecapital.data.mappers

import com.example.scelablecapital.utils.Safe
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import kotlin.concurrent.withLock

class DateMapperImpl @Inject constructor() : DateMapper {

    //2013-10-02T23:58:48Z
    private val formatter = SynchronizedSimpleDateFormatWrapper("yyyy-dd-MM")

    override fun map(date: String): Date? {
        return formatter.parse(date)
    }

    private class SynchronizedSimpleDateFormatWrapper(pattern: String) {

        private val simpleDateFormatImpl = SimpleDateFormat(pattern, Locale.getDefault())
        private val lock = ReentrantLock()

        var dateFormatSymbols: DateFormatSymbols
            get() = simpleDateFormatImpl.dateFormatSymbols
            set(value) {
                simpleDateFormatImpl.dateFormatSymbols = value
            }

        fun format(date: Date): String {
            return lock.withLock { simpleDateFormatImpl.format(date) }
        }

        fun parse(dateStr: String?): Date? {
            return lock.withLock {
                Safe {
                    require(dateStr != null)
                    simpleDateFormatImpl.parse(dateStr)
                }.consumeError {

                }
            }
        }

    }
}