package ch.rmy.android.http_shortcuts.activities.categories.usecases

import androidx.annotation.CheckResult
import ch.rmy.android.framework.extensions.runIf
import ch.rmy.android.framework.utils.localization.Localizable
import ch.rmy.android.framework.viewmodel.viewstate.DialogState
import ch.rmy.android.http_shortcuts.R
import ch.rmy.android.http_shortcuts.activities.categories.CategoriesViewModel
import ch.rmy.android.http_shortcuts.data.domains.categories.CategoryId

class GetContextMenuDialogUseCase {

    @CheckResult
    operator fun invoke(
        categoryId: CategoryId,
        title: Localizable,
        hideOptionVisible: Boolean,
        showOptionVisible: Boolean,
        changeLayoutTypeOptionVisible: Boolean,
        placeOnHomeScreenOptionVisible: Boolean,
        deleteOptionVisible: Boolean,
        viewModel: CategoriesViewModel,
    ) =
        DialogState.create {
            title(title)
                .item(R.string.action_rename) {
                    viewModel.onRenameCategoryOptionSelected(categoryId)
                }
                .runIf(showOptionVisible) {
                    item(R.string.action_show_category) {
                        viewModel.onCategoryVisibilityChanged(categoryId, hidden = false)
                    }
                }
                .runIf(hideOptionVisible) {
                    item(R.string.action_hide_category) {
                        viewModel.onCategoryVisibilityChanged(categoryId, hidden = true)
                    }
                }
                .runIf(changeLayoutTypeOptionVisible) {
                    item(R.string.action_change_category_layout_type) {
                        viewModel.onLayoutTypeOptionSelected(categoryId)
                    }
                        .item(R.string.action_change_category_background) {
                            viewModel.onBackgroundTypeOptionSelected(categoryId)
                        }
                }
                .runIf(placeOnHomeScreenOptionVisible) {
                    item(R.string.action_place_category) {
                        viewModel.onPlaceOnHomeScreenSelected(categoryId)
                    }
                }
                .runIf(deleteOptionVisible) {
                    item(R.string.action_delete) {
                        viewModel.onCategoryDeletionSelected(categoryId)
                    }
                }
                .build()
        }
}
