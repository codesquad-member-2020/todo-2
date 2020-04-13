import "../style/style.scss";
import { getTodoData } from "./todo/todo";

window.addEventListener('DOMContentLoaded', () => {
    const projectId = "test";
    getTodoData(projectId);
});