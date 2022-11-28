# BIBLE APP

## First delivery (28/11/2022)
This application serves the function of saving and showing bible verses created by the user.
During the development of this version, we have worked with:
- Activities
- Fragments
- Basic interface elements like EditText, Label, Button and TextView
- SQLite (insert, delete and select)
- Recycler View
- Alert Dialog

### App tour

#### Login
<img src="readme_images/Login.jpg" height="512">

> When you open the app, the first you see is the Login Activity. You must use **admin** for the username field and **admin** for the password field to login correctly. If done, you will go to the Bottom Navigation Activity, and a *Login successful* Toast will appear. If the fields are not these, a *Login unsuccessful* Toast will show. On both cases, username and password fields will be cleared.

<br>

#### Home
<img src="readme_images/Home.jpg" height="512">

> The first Fragment that will load after the login is Home. Right now, is just a basic text welcoming the user. To change the active Fragment, there is a Bottom Navigation Menu at the bottom, with three possible options: Home, Form and List.

<br>

#### Form
<p>
  <img src="readme_images/Form.jpg" height="512">
  <img src="readme_images/Delete.jpg" height="512">
</p>

> The Form Fragment allows the user the possibility of adding new verses to the list. The user must specify four fields: Book, Chapter, Verse and Text. Chapter and Verse must be numbers, so the keyboard for these fields must appear different, with just number buttons. When trying to create clicking the button, if all fields are filled a Toast will show saying that the creation has been successful. But if some field is empty, the verse will not be added and a Toast will notify it. As the Text field might be long, the input container will grow as text is being written. At the bottom there's also a button to delete all verses from the list. When clicked, a dialog menu will appear, and the user will have to confirm the decision of deleting all data. Whatever the choice is, a Toast will show with a message.

<br>

#### List
<p>
  <img src="readme_images/Empty.jpg" height="512">
  <img src="readme_images/List.jpg" height="512">
</p>

> The List Fragment uses a Recycler View to display a list of all the verses, but if the list is empty, a *List is empty* placeholder is shown instead. Every item of the list will correspond to a verse from the database. The title will be shown, formed by the Book, the Chapter and the Verse. There will be a **Delete** button aswell. Like on the **Delete all** button from the form, a dialog menu will show to confirm the action. If an element is clicked, the Detail Fragment of that verse will be loaded.

<br>

#### Detail
<img src="readme_images/Detail.jpg" height="512">

> The Detail Fragment is very basic, with a title with the same format from the one on the Recycler View item, and after that the text from the verse is shown. Both TextViews are formatted so multiple-line text is allowed, with paddings on left and right. On this Fragment, the Bottom Navigation Menu is hidden, and to go back to the List, the back button from the phone must be clicked. Obviously, when that is done, the Bottom Navigation Menu is available again.
