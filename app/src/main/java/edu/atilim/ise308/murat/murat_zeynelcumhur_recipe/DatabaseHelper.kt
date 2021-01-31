package edu.atilim.ise308.murat.murat_zeynelcumhur_recipe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context,DatabaseHelper.DATABASE_NAME,null,DatabaseHelper.DATABASE_VERSION) {
    private val TABLE_NAME="Recipe"
    var foodName: String? = null
    var insideMaterials: String? = null
    var howToMake: String? = null
    var calories: Double = 0.0
    var haveBeenTried: Int = 1

    private val COL_ID = "id"
    private val COL_NAME = "food_name"
    private val COL_MATERIALS = "materials"
    private val COL_HOW_TO_MAKE = "how_to_make"
    private val COL_CALORIES = "calories"
    private val COL_TRIED = "tried"
    companion object {
        private val DATABASE_NAME = "Recipe_App_Database"//database adı
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME  VARCHAR(256),$COL_MATERIALS  VARCHAR(256),$COL_HOW_TO_MAKE  VARCHAR(512),$COL_CALORIES DOUBLE,$COL_TRIED INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    //insertion data
    fun insertData(recipe:Recipe){
        val sqliteDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME , recipe.foodName)
        contentValues.put(COL_MATERIALS, recipe.insideMaterials)
        contentValues.put(COL_HOW_TO_MAKE, recipe.howToMake)
        contentValues.put(COL_TRIED, recipe.haveBeenTried)

        val result = sqliteDB.insert(TABLE_NAME,null,contentValues)

        Toast.makeText(context,if(result != -1L) "Kayıt Başarılı" else "Kayıt yapılamadı.", Toast.LENGTH_SHORT).show()
    }
    //read data
    fun readData():MutableList<Recipe>{
        val recipeList = mutableListOf<Recipe>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val recipe = Recipe()
                recipe.foodId = result.getString(result.getColumnIndex(COL_ID)).toInt()
                recipe.foodName = result.getString(result.getColumnIndex(COL_NAME))
                recipe.insideMaterials = result.getString(result.getColumnIndex(COL_MATERIALS))
                recipe.howToMake = result.getString(result.getColumnIndex(COL_HOW_TO_MAKE))
                recipe.haveBeenTried = result.getString(result.getColumnIndex(COL_TRIED)).toInt()
                recipeList.add(recipe)
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return recipeList
    }
    fun update(tried:Int) {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_TRIED=tried"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val cv = ContentValues()
                if (tried==0){
                    cv.put(COL_TRIED,(result.getInt(result.getColumnIndex(COL_TRIED))+1))
                    db.update(TABLE_NAME,cv, "$COL_ID=? AND $COL_NAME=?",
                        arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                            result.getString(result.getColumnIndex(COL_NAME))))
                }
                else if (tried == 1){
                    cv.put(COL_TRIED,(result.getInt(result.getColumnIndex(COL_TRIED))-1))
                    db.update(TABLE_NAME,cv, "$COL_ID=? AND $COL_NAME=?",
                        arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                            result.getString(result.getColumnIndex(COL_NAME))))
                }
            }while (result.moveToNext())
        }

        result.close()
        db.close()
    }
    //delete
    fun deleteAllData(){
        val sqliteDB = this.writableDatabase
        sqliteDB.delete(TABLE_NAME,null,null)
        sqliteDB.close()

    }

}