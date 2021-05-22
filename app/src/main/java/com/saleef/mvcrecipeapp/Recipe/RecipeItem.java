package com.saleef.mvcrecipeapp.Recipe;

public class RecipeItem {

    private String id;
    private String thumbnail;
    private String name;


      public RecipeItem(String thumbnail,String name){
          this.thumbnail = thumbnail;
          this.name = name;
      }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
