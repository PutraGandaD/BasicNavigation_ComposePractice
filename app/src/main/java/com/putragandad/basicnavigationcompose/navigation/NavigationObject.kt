package com.putragandad.basicnavigationcompose.navigation

import kotlinx.serialization.Serializable

// serializable used for Type-safe navigation

// serializable for Authentication Route (which is nested)
@Serializable data object AuthBaseGraph

// serializable for Route(child) inside Authentication Graph(parent/base)
@Serializable data object LoginRoute
@Serializable data object RegisterRoute

// serializable for Main Top Level Destination nested graph
// each graph represent nested graph
@Serializable data object ForYouGraph
@Serializable data object SearchGraph
@Serializable data object LibraryGraph

// serializable for Route(child) inside nested Top Level Destination graph
@Serializable data object ForYouRoute
@Serializable data object SearchRoute
@Serializable data object LibraryRoute
