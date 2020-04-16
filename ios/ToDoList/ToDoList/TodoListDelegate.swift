//
//  TodoListDelegate.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/15.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation
import UIKit

extension TodoListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, contextMenuConfigurationForRowAt indexPath: IndexPath, point: CGPoint) -> UIContextMenuConfiguration? {
        let configuration = UIContextMenuConfiguration(identifier: nil, previewProvider: nil) { _ in
            guard let tableViewDataSource = tableView.dataSource as? TodoListDataSource else { return UIMenu(title: "") }
            
            let moveToDone = UIAction(title: "move to done") { _ in
                guard let moveCard = tableViewDataSource.cardList?.cards[indexPath.row] else { return }
                NotificationCenter.default.post(name: .moveCardToDone, object: nil, userInfo: ["moveCard" : moveCard])
                self.deleteSelfRow(dataSource: tableViewDataSource, indexPath: indexPath)
            }
            let delete = UIAction(title: "delete", attributes: .destructive) { _ in
                self.deleteSelfRow(dataSource: tableViewDataSource, indexPath: indexPath)
            }
            
            let menu = UIMenu(title: "", children: [moveToDone, delete])
            
            if tableViewDataSource.cardList?.id == 3 {
                return UIMenu(title: "", children: [delete])
            }
            
            return menu
        }
        return configuration
    }
    
    private func deleteSelfRow(dataSource: TodoListDataSource, indexPath: IndexPath) {
        APIClient.apiClient.requestDeleteCard(categoryId: dataSource.cardList!.id, cardId: dataSource.cardList!.cards[indexPath.row].id)
        tableViewDataSource.cardList?.cards.remove(at: indexPath.row)
        NotificationCenter.default.post(name: .deleteRow, object: self, userInfo: ["deleteRow":indexPath])
    }
}
