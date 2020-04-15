import "../style/style.scss";
import { getTodoData, renderTodo, eventHandler } from "./todo/todo";

window.addEventListener("DOMContentLoaded", () => {
    const projectId = 1;
    getTodoData(projectId).then(data => {
        renderTodo(data);
    }).then(() => {
        eventHandler();
    });
});