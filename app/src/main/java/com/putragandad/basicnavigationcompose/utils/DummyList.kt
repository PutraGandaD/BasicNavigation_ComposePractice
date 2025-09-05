package com.putragandad.basicnavigationcompose.utils

import com.putragandad.basicnavigationcompose.models.AuthorList

object DummyList {
    val dummySearchItems = List(20) { index ->
        AuthorList(
            id = index + 1,
            author = "Author ${index + 1}",
            description = "This is a sample description for item ${index + 1}. " +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        )
    }
}