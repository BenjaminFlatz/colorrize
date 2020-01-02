package com.example.e4

class SwipeModel {

    var name: String? = null
    var image_drawable: Int = 0

    fun getImage_drawables(): Int {
        return image_drawable
    }

    fun setImage_drawables(image_drawable: Int) {
        this.image_drawable = image_drawable
    }


    fun getNames(): String {
        return name.toString()
    }

    fun setNames(name: String) {
        this.name = name
    }

}