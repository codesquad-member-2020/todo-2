//
//  CardDetailData.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/11.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

struct CardDetailData: Codable {
    var cardId: Int
    var user_name: String
    var title: String
    var content: String
}
