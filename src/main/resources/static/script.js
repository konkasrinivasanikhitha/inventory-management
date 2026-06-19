const BASE_URL = "http://localhost:8083/api";

function registerUser(){

    const user = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    fetch(BASE_URL + "/register",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(user)
    })
    .then(response=>response.json())
    .then(data=>{
        alert("Registration Successful");
        window.location.href="login.html";
    })
    .catch(error=>{
        console.error(error);
        alert("Registration Failed");
    });
}

function loginUser(){

    const loginData = {
        email: document.getElementById("loginEmail").value,
        password: document.getElementById("loginPassword").value
    };

    fetch(BASE_URL + "/login",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(loginData)
    })
    .then(response=>response.json())
    .then(data=>{

        if(data.userId){

            localStorage.setItem("userId",data.userId);

            alert("Login Successful");

            window.location.href="profile.html";
        }
        else{
            alert("Invalid Credentials");
        }

    })
    .catch(error=>{
        console.error(error);
        alert("Login Failed");
    });
}

function loadProfile(){

    const userId = localStorage.getItem("userId");

    fetch(BASE_URL + "/profile/" + userId)
    .then(response=>response.json())
    .then(data=>{

        document.getElementById("userId").innerText=data.userId;
        document.getElementById("name").innerText=data.name;
        document.getElementById("email").innerText=data.email;
        document.getElementById("role").innerText=data.role;

    });
}

function updateProfile(){

    const userId = localStorage.getItem("userId");

    const updatedUser = {

        name: document.getElementById("newName").value,
        email: document.getElementById("newEmail").value

    };

    fetch(BASE_URL + "/profile/" + userId,{
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(updatedUser)
    })
    .then(response=>response.json())
    .then(data=>{

        alert("Profile Updated Successfully");

        location.reload();

    });

}