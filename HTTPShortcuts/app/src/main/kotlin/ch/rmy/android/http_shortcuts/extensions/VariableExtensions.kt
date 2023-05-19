package ch.rmy.android.http_shortcuts.extensions

import ch.rmy.android.http_shortcuts.activities.execute.DialogHandle
import ch.rmy.android.http_shortcuts.data.models.Shortcut
import ch.rmy.android.http_shortcuts.variables.VariableManager
import ch.rmy.android.http_shortcuts.variables.VariableResolver

suspend fun VariableResolver.resolve(
    variableManager: VariableManager,
    shortcut: Shortcut,
    dialogHandle: DialogHandle,
): VariableManager {
    val requiredVariableIds = VariableResolver.extractVariableIdsExcludingScripting(shortcut)
    return resolve(variableManager, requiredVariableIds, dialogHandle)
}
