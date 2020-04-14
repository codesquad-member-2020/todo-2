//
//  CategoryData.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/11.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

// 각 카테고리 별로 리스트를 나타내주기 위함.
// 카드 수정과 같은 변경점은 cards의 배열의 데이터를 바꾸어 주어야 하므로 여기서 처리한다.
struct CategoryData: Codable {
    var category_id: Int
    var title: String
    var cards: [CardDetailData]
    
    func title(index: Int) -> String {
        return cards[index].title
    }
    
    func content(index: Int) -> String {
        return cards[index].content
    }
}
