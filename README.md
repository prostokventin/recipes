# Реализация проекта Recipe

Ссылка на проект: https://hyperskill.org/projects/180

## Примеры использования (взяты из учебных материалов последней стадии проекта)

- Веб сервер запускается ```8881``` порту
- Доступ к h2 базе данных: ```http://localhost:8881/h2```

### Example 1
```POST /api/recipe/new``` request without authentication
```
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

Status code: ```401 (Unauthorized)```

### Example 2
```POST /api/register``` request without authentication
```
{
   "email": "Cook_Programmer@somewhere.com",
   "password": "RecipeInBinary"
}
```
Status code: ```200 (Ok)```

Further ```POST /api/recipe/new``` request with basic authentication; email (login): ```Cook_Programmer@somewhere.com```, and password: ```RecipeInBinary```
```
{
   "name": "Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

Response:
```
{
   "id": 1
}
```
Further ```PUT /api/recipe/1``` request with basic authentication; email (login): ```Cook_Programmer@somewhere.com```, password: ```RecipeInBinary```
```
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```
Status code: ```204 (No Content)```

Further ```GET /api/recipe/1``` request with basic authentication; email (login): ```Cook_Programmer@somewhere.com```, password: ```RecipeInBinary```

Response:
```
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "date": "2020-01-02T12:11:25.034734",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```
### Example 3
POST ```/api/register``` request without authentication
```
{
   "email": "CamelCaseRecipe@somewhere.com",
   "password": "C00k1es."
}
```
Status code: ```200 (Ok)```

Further response for the ```GET /api/recipe/1``` request with basic authentication; email (login): ```CamelCaseRecipe@somewhere.com```, password: ```C00k1es.```
```
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "date": "2020-01-02T12:11:25.034734",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```
Further ```PUT /api/recipe/1``` request with basic authentication; email (login): ```CamelCaseRecipe@somewhere.com```, password: ```C00k1es.```
```
{
   "name": "Warming Ginger Tea",
   "category": "beverage",
   "description": "Ginger tea is a warming drink for cool weather, ...",
   "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
   "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]
}
```
Status code: ```403 (Forbidden)```

Further ```DELETE /api/recipe/1``` request with basic authentication; email (login): ```CamelCaseRecipe@somewhere.com```, password: ```C00k1es.```

Status code: ```403 (Forbidden)```
