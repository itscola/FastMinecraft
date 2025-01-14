package me.luna.fastmc.shared.util

import me.luna.fastmc.FastMcMod
import me.luna.fastmc.shared.opengl.glUniformMatrix4fv
import org.joml.Matrix4f
import java.nio.FloatBuffer

object MatrixUtils {
    val matrixBuffer: FloatBuffer = BufferUtils.float(16)

    fun putMatrix(matrix: Matrix4f): MatrixUtils {
        matrix.get(matrixBuffer)
        return this
    }

    fun getMatrix(): Matrix4f {
        return Matrix4f(matrixBuffer)
    }

    fun uploadMatrix(location: Int) {
        glUniformMatrix4fv(location, FastMcMod.glWrapper.rowMajor, matrixBuffer)
    }
}