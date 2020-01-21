# chatClient

# IMPLEMENTED FEATURES:
1. Sending and receiving messages from server
2. Receiving and displaying chat history from server
3. Receiving and displaying all active chatusers registered with server
4. Implemented options menu for main and username activity
5. Implemented recycler view for displaying elements in main activity, chathistory
   activity and chatusers activity
6. On click listener implemented for elements in recycler view
7. Implemented alert dialog fragments for changing IP address and text message colour
8. Implemented shared preferences for saving colour preference of user


# OO DESIGN PATTERN USED IN CLIENT APPLICATION: 1. MODEL VIEW CONTROLLER PATTERN (MVC)
Used MVC pattern in my Chat Client Design. Divided my code in two directories i.e. UI part and Logic part
# UI part: Behaves as view part of MVC (all activities and associated classes like recycler view adapter class, dialog fragment class etc.)
# Logic part: Behaves as model part of MVC (all custom classes except ChatClientController object class)
# ChatClientController (object class): Behaves as controller part of MVC

Implemented chat client design such that model and view parts interact with each through Controller part i.e. ChatClientController. No direct communication between model and view part.

# 2. OBSERVER PATTERN:
Used observer pattern for updating UI when new messages are received or sent. Declared ChatClientController as observer and ChatClientDatabase as observable. Every time ChatClientController is updated, it takes care of updating UserInterface.
