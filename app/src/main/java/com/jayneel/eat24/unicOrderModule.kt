package com.jayneel.eat24

data class unicOrderModule(
    var tbbillst:String?=null,
    var unicid:String?=null,
    var order: String?=null,
    var tabid:String?=null) {
    constructor() : this("", "","","")
}