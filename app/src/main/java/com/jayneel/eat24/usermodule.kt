package com.jayneel.eat24

data class usermodule(var uid:String?=null,var utype:String?=null,var upass:String?=null) {
    constructor() : this("", "", "")
}