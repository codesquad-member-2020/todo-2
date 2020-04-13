//
//  CardDetailData.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/11.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

// 카드 디테일 뷰를 위한 모델
struct CardDetailData: Codable {
    var cardId: Int
    var user_name: String
    var title: String
    var content: String
}
