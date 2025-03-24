package com.example.fefu_course.presentation.vo.mapper

interface ViewMapper<FROM, TO> {
    fun map(from: FROM): TO

    fun map(fromList: Collection<FROM>): List<TO> {
        val result = ArrayList<TO>()
        fromList.mapTo(result) { map(it) }
        return result
    }
}
