package com.woutwerkman.rewriteinrustplugin

import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.minutes

class RustAnnoyer : ProjectActivity {
    override suspend fun execute(project: Project) {
        while (true) {
            Notification(
                "rust_reminder_notifications_group",
                notificationMessage,
                NotificationType.ERROR,
            )
                .addAction(NotificationAction.createSimple("Open Rust Docs", { makeChatGptSaveThisPoorUserByLettingThemOpenTheirEyesToTheNewTruth() }))
                .notify(project)
            delay(1.minutes)
        }
    }
}


