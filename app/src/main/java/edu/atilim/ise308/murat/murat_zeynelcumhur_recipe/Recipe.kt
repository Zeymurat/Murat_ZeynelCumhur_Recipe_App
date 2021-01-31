package edu.atilim.ise308.murat.murat_zeynelcumhur_recipe


class Recipe {
    var foodId: Int = 1
    var foodName: String? = null
    var insideMaterials: String? = null
    var howToMake: String? = null
    var haveBeenTried: Int = 1


    constructor(
        //foodId: Int = 1,
        foodName: String ="",
        insideMaterials: String="",
        howToMake: String="",
        haveBeenTried: Int = 1
    ) {
        //this.foodId= foodId
        this.foodName = foodName
        this.insideMaterials = insideMaterials
        this.howToMake = howToMake
        this.haveBeenTried = haveBeenTried
    }

}
