import "../style/style.scss";
import { getTodoData, renderTodo, eventHandler } from "./todo/todo";

window.addEventListener("DOMContentLoaded", () => {
    const projectId = "test";
    getTodoData(projectId).then(data => {
        renderTodo(data);
        eventHandler();
    });
});