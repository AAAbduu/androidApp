import com.example.androidfinalassignment.R


/**
 * OnBoardViewList is the list of OnBoardViews that are shown in the OnBoardScreens.
 */
enum class OnBoardViewsList(val onBoardView: OnBoardView) {
    WELCOME(
        OnBoardView(
            image = R.drawable.logo_color,
            title = "Welcome to MyMeals!",
            description = ""
        )
    ),
    MEAL_PLAN(
        OnBoardView(
            image = R.drawable.on_board_view3,
            title = "Whole meal plan ready every day!",
            description = "You will have a menu for the day prepared with as many meals as you selected and according to your necessities."
        )
    ),
    PERSONALIZED_MEALS(
        OnBoardView(
            image = R.drawable.on_board_view2,
            title = "Personalized meals!",
            description = "Personalized meals according to your preferences and adapted to your food allergies."
        )
    );

    companion object {
        fun valuesList(): List<OnBoardView> {
            return values().map { it.onBoardView }
        }
    }
}

data class OnBoardView(
    val title: String,
    val description: String,
    val image: Int
)