//
//  NotificationExtension.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/10.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

extension Notification.Name {
    static let deleteRow = NSNotification.Name("deleteRow")
    static let completeLoad = NSNotification.Name("completeLoad")
    static let moveCardToDone = NSNotification.Name("moveCardToDone")
    static let reloadData = NSNotification.Name("reloadData")
    static let completeHistoryLoad = NSNotification.Name("completeHistoryLoad")
}
