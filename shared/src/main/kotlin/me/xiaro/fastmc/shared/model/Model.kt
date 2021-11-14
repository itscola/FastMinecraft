package me.xiaro.fastmc.shared.model

import me.xiaro.fastmc.shared.opengl.*
import me.xiaro.fastmc.shared.resource.Resource

abstract class Model(override val resourceName: String, private val textureSizeX: Int, private val textureSizeZ: Int) :
    Resource {
    private var vboID = 0
    var modelSize = 0; private set

    private fun init0() {
        val builder = ModelBuilder(0, textureSizeX, textureSizeZ)
        builder.buildModel()

        vboID = glGenBuffers()
        modelSize = builder.vertexSize

        glBindBuffer(GL_ARRAY_BUFFER, vboID)
        glBufferData(GL_ARRAY_BUFFER, builder.build(), GL_STATIC_DRAW)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
    }

    protected abstract fun ModelBuilder.buildModel()

    fun attachVBO() {
        glBindBuffer(GL_ARRAY_BUFFER, vboID)

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 20, 0L) // 12
        glVertexAttribPointer(1, 2, GL_UNSIGNED_SHORT, true, 20, 12L) // 4
        glVertexAttribPointer(2, 3, GL_BYTE, false, 20, 16L) // 3
        glVertexAttribIPointer(3, 1, GL_UNSIGNED_BYTE, 20, 19L) // 1

        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)
        glEnableVertexAttribArray(2)
        glEnableVertexAttribArray(3)
    }

    override fun destroy() {
        glDeleteBuffers(vboID)
    }

    companion object {
        fun <T : Model> T.init() :T {
            this.init0()
            return this
        }
    }
}