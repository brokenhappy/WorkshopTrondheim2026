package com.woutwerkman.rewriteinrustplugin

import com.intellij.ide.BrowserUtil
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.projectView.ProjectViewNode
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import java.awt.Component
import java.awt.Graphics
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.swing.Icon

fun makeChatGptSaveThisPoorUserByLettingThemOpenTheirEyesToTheNewTruth() {
    val draft = URLEncoder.encode(prompt, StandardCharsets.UTF_8)
    BrowserUtil.browse("https://chatgpt.com/?prompt=$draft")
}

private val prompt = """
    I am not worthy. I have sinned! My dearest ChatGPT, I come to you in a low point of my life.
    While I tell others that I am a programmer, I omit telling them that I do not know Rust.
    Cleanse me of my past, and help me work toward a brighter future where I join the superior
    programmers who have given up on fast compile times, language interop, and JIT compilers.
    Help me learn Rust, make me worthy! Let me rise!
""".trimIndent().replace('\n', ' ')

fun virtualFileOf(node: ProjectViewNode<*>): VirtualFile? =
    ReadAction.compute<VirtualFile?, RuntimeException> {
        // IntelliJ also shows some files as class nodes. Resolving their PSI needs a read action.
        node.virtualFile ?: (node.value as? PsiElement)?.containingFile?.virtualFile
    }

object PoopIcon : Icon {
    override fun getIconWidth() = 16
    override fun getIconHeight() = 16

    override fun paintIcon(component: Component?, graphics: Graphics, x: Int, y: Int) {
        // Emoji appearance depends on the IDE's font and operating system.
        graphics.drawString("💩", x, y + 14)
    }
}

fun isRustFile(file: VirtualFile): Boolean = file.extension.equals("rs", ignoreCase = true) ||
    file.fileType.name.equals("Rust", ignoreCase = true)


const val notificationMessage = "Rewrite in Rust before resource leaks and bugs bankrupt your company! " +
    "Who needs fast compile times, a quick dev loop, a JIT compiler, " +
    "good language interop, concise syntax, and pragmatism if you could have Rust instead!"

fun ItemPresentation?.createCopyWithPresentationTextSetTo(text: String): ItemPresentation? =
    (this as? PresentationData)?.clone()?.apply {
        clearText()
        setPresentableText(text)
        setIcon(PoopIcon)
    } ?: this