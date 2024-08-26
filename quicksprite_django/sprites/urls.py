from django.urls import path,include
from . import views

app_name = 'sprites'

urlpatterns = [
   path("", views.selectProject, name='selectProject' ),
   path("newproject", views.newProject, name="newProject"),
   path("newspritesheet", views.newSpritesheet, name="newSpritesheet"),
   path("updateproject/<pID>", views.updateProject, name="updateProject"),
   path("updatespritesheet/<sID>", views.updateSpritesheet, name="updateSpritesheet"),
   path("deleteproejct/<pID>", views.deleteProejct, name="deleteProject"),
   path("deleteSpritesheet/<sID>", views.deleteSpritesheet, name="deleteSpritesheet"),
   path("projects/<pID>", views.listSpritesheets, name="listSpritesheets"),
   path("spritesheet/<sID>/animate", views.animate, name="animate"),
]