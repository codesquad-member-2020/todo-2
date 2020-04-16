//
//  todoListDataSource.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/09.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class TodoListDataSource: NSObject, UITableViewDataSource {
    var handler: () -> () = {}
    
    var cardList: CategoryData? {
        didSet {
            handler()
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return cardList?.cards.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "TaskCell", for: indexPath) as? TaskCardCell else { return UITableViewCell() }
        
        cell.taskTitleLabel.text = cardList?.cards[indexPath.row].title
        return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            APIClient.apiClient.requestDeleteCard(categoryId: cardList!.id, cardId: cardList!.cards[indexPath.row].id)
            self.cardList?.cards.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .automatic)
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                NotificationCenter.default.post(name: .reloadData, object: self, userInfo: nil)
            }
        }
    }
}
