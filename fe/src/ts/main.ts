import "../style/style.scss";
import { getProjectData, renderProject, eventHandler } from "./todo/todo";

window.addEventListener("DOMContentLoaded", () => {
    const projectId = 1; // 아직 JWT 통신 미구현이라 하드 코딩
    getProjectData(projectId).then(data => {
        renderProject(data);
    }).then(() => {
        eventHandler();
    });
});