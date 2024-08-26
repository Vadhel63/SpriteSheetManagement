import requests
import base64
import json
from django.shortcuts import render,redirect
from django.urls import reverse

headers = {
    'Content-Type': 'application/json'
}

# Create your views here.
def selectProject(request):
    if request.method == "POST":
        project = request.POST['project']
        return redirect('sprites:listSpritesheets', project)
    else:
        response = requests.get("http://localhost:8080/api/projects")
        projects = response.json()
        return render(request,'project_select.html', {
            'projects' : projects,
        })

def listSpritesheets(request, pID):
    response = requests.get(f'http://localhost:8080/api/projects/{pID}')
    project = response.json()
    response = requests.get(f"http://localhost:8080/api/projects/{pID}/spritesheets")
    spritesheets = response.json()
    return render(request,'list_spritesheets.html',{
        "project" : project,
        "spritesheets":spritesheets
        })

def animate(request, sID):
    response = requests.get(f"http://localhost:8080/api/projects/1/spritesheets/{sID}")
    spritesheet = response.json()
    return render(request,'animate.html', {
        "spritesheet":spritesheet,
    })

def newProject(request):
    if request.method == "POST":
        project = json.dumps({
            "name" : request.POST['name']
        })
        requests.post('http://localhost:8080/api/projects', data=project, headers=headers)
        return redirect("sprites:selectProject")
    else:
        return render(request,'create_project.html')

def newSpritesheet(request):
    if request.method == "POST":
        spritesheet = json.dumps({
            "name" : request.POST['name'],
            'imageData': base64.b64encode(request.FILES['spritesheet'].read()).decode('utf-8'),
            'spriteWidth': int(request.POST['spriteWidth']),
            'spriteHeight': int(request.POST['spriteHeight']),
        })
        pID = request.POST['project']
        requests.post(f'http://localhost:8080/api/projects/{pID}/spritesheets', data=spritesheet, headers=headers)
        return redirect('sprites:listSpritesheets',pID)
    else:
        response = requests.get("http://localhost:8080/api/projects")
        projects = response.json()
        return render(request,'create_spritesheet.html',{
            'projects':projects,
        })

def updateProject(request, pID):
    if request.method == "POST":
        project = json.dumps({
            "id": pID,
            "name" : request.POST['name']
        })
        requests.put(f'http://localhost:8080/api/projects/{pID}', data=project, headers=headers)
        return redirect("sprites:selectProject")
    else:
        project = requests.get(f'http://localhost:8080/api/projects/{pID}')
        project = project.json()
        return render(request,"update_project.html", {
            'project' : project,
        })

def updateSpritesheet(request, sID):
    if request.method == "POST":
        spritesheet = json.dumps({
            "id":sID,
            "name" : request.POST['name'],
            'imageData': base64.b64encode(request.FILES['spritesheet'].read()).decode('utf-8'),
            'spriteWidth': request.POST['spriteWidth'],
            'spriteHeight': request.POST['spriteHeight'],
        })
        pID = request.POST['project']
        requests.put(f'http://localhost:8080/api/projects/{pID}/spritesheets/{sID}', data=spritesheet, headers=headers)
        return redirect('sprites:listSpritesheets', pID)
    else:
        spritesheet = requests.get(f'http://localhost:8080/api/projects/1/spritesheets/{sID}')
        spritesheet = spritesheet.json()
        response = requests.get("http://localhost:8080/api/projects")
        projects = response.json()
        return render(request, 'update_spritesheet.html',{
            'spritesheet':spritesheet,
            'projects' : projects,
        })


def deleteProejct(request, pID):
    if request.method == "POST":
        requests.delete(f'http://localhost:8080/api/projects/{pID}')
        return redirect("sprites:selectProject")
    else:
        return render(request,"delete_project.html")

def deleteSpritesheet(request, sID):
    if request.method == "POST":
        requests.delete(f'http://localhost:8080/api/projects/1/spritesheets/{sID}')
        return redirect("sprites:selectProject")
    else:
        return render(request, 'delete_spritesheet.html')



