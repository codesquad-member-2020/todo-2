export interface ICard {
    cardId: number,
    title: string,
    content: string,
    user_name: string
}

export interface IColumn {
    category_id: number,
    title: string,
    cards: Array<ICard>
}