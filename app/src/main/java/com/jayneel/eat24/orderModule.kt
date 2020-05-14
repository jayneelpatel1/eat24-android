package com.jayneel.eat24

data class orderModule(var mnn:String?=null,var qty:Int?=null,var ord:Int?=null,var price:Int?=null) {
    constructor() : this("", 0, 0,0)
}