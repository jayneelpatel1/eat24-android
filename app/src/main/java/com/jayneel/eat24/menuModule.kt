package com.jayneel.eat24

data class menuModule(var mnm:String?=null,var mprice:Int?=null,var mcat:String?=null) {
    constructor() : this("", 0, "")
}