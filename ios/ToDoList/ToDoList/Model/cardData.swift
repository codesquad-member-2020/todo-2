//
//  cardData.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/10.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

struct cardData: Codable {
    
    struct cardDetailData: Codable {
        var cardId: Int
        var user_name: String
        var title: String
        var content: String
    }
    
    struct categoryData: Codable {
        var category_id: Int
        var title: String
        var cards: [cardDetailData]
    }
    
    var result: String
    var data: [categoryData]
    
}
