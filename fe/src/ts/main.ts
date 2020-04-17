import "../style/style.scss";
import { getProjectData, renderProject, eventHandler } from "./todo/todo";

window.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem("projectId", "1");
    localStorage.setItem("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoic2NvdHQiLCJ1c2VySWQiOjEsImV4cCI6MTU4NzE4Nzk3OH0.AGICyVXC3SQWqr9dEFNw7NM2ZPEuPqi9mr_AjTL0kP0");
    getProjectData().then(data => {
        renderProject(data);
    }).then(() => {
        eventHandler();
    });
});