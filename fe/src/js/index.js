import '../style/style.scss';

async function getTodoList() {
    const options = {
        method: 'GET',
        headers: {
            // Accept: 'text/plain;charset=UTF-8',
            'Content-Type': 'text/plain;charset=UTF-8',
            'Access-Control-Allow-Origin': '*'
        }
    };
    const response = await fetch(`http://15.164.28.20:8080/mock/projects/1`, options);
    const result = await response.json();
    // console.log('result check', result.data);
    makeColumnList(result.data);
}
function makeColumnList(data) {
    const content = document.querySelector('.content');
    data.forEach(columns => {
        content.innerHTML+= 
        `<div class="column">
        <div class="column-top">
            <div class="column-total">${columns.cards.length}</div>
            <div class="column-title">${columns.title}</div>
            <div class="btn-add"><img src="../src/img/add-24px.svg" class="btn-add-img"></div>
            <div class="column-btn-close">
                <img src="../src/img/close-24px.svg" class="column-btn-close-img">
            </div>
        </div>
        <!-- 숨길 입력창 -->
        <div class="add-card-wrap">
            <div class="add-card-title">
                <label for="add-card-title" hidden></label>
                <textarea name="addCardTitle" id="add-card-title"></textarea>
            </div>
            <div>
                <button class="card-btn-add">Add</button>
                <button class="card-btn-cancel">Cancel</button>
            </div>
        </div>
        <div class="card-wrap" id="cards${columns.category_id}">
        </div>
    </div>`;
});
content.innerHTML += '<div class="column-add"><img src="../src/img/add-24px.svg" class="btn-add-white-img"> Add another list</div>';
makeCardList(data);
}
function makeCardList(data) {
    for(let i=0; i < data.length; i++) {
        const cardWrap = document.getElementById(`cards${data[i].category_id}`);
        for(let j=0; j < data[i].cards.length; j++) {
            cardWrap.innerHTML+=
            `<div class="card">
                <div class="card-top">
                    <div class="card-icon"><img src="../src/img/list_alt-24px.svg" class="card-icon-img"></div>
                    <div class="card-title">${data[i].cards[j].title}</div>
                    <div class="btn-close"><img src="../src/img/close-24px.svg" class="btn-close-img"></div>
                </div>
                <div class="card-writer">Added by ${data[i].cards[j].user_name}</div>
            </div>`
        }
    }
}
window.addEventListener('DOMContentLoaded', () => {
    getTodoList();
});