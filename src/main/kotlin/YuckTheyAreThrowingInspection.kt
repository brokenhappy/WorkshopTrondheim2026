package com.woutwerkman.rewriteinrustplugin

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool
import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.codeInspection.util.IntentionFamilyName
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaElementVisitor
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiMethod

class YuckTheyAreThrowingInspection : AbstractBaseJavaLocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : JavaElementVisitor() {
            override fun visitMethod(method: PsiMethod) {
                val throwsList = method.throwsList
                if (throwsList.referenceElements.isEmpty()) return
                holder.registerProblem(
                    throwsList,
                    "Exceptions?! Yuck! Exceptions should not be exceptional, they should be impossible! Use and Love Rust instead.",
                    Yucky()
                )
            }
        }
}

class Yucky : LocalQuickFix {
    override fun getFamilyName(): @IntentionFamilyName String = "Yucky"

    override fun applyFix(
        project: Project,
        descriptor: ProblemDescriptor,
    ) {
        makeChatGptSaveThisPoorUserByLettingThemOpenTheirEyesToTheNewTruth()
    }

}