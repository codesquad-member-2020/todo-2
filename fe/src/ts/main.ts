import "../style/style.scss";
import { getProjectData, renderProject, eventHandler } from "./todo/todo";

window.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem("projectId", "1");
    localStorage.setItem("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoic2NvdHQiLCJ1c2VySWQiOjEsImV4cCI6MTU4NzEwMTQ1OH0.yhOmcW4hQioS9PclsZaM3CoU-PksKMY9amRXP3ltTR8");
    getProjectData().then(data => {
        renderProject(data);
    }).then(() => {
        eventHandler();
    });
});