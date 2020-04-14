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
            
            let moveToDone = UIAction(title: "move to done") { _ in }
            let delete = UIAction(title: "delete", attributes: .destructive) { _ in
                tableViewDataSource.cardList?.remove(at: indexPath.row)
                NotificationCenter.default.post(name: .deleteRow, object: self, userInfo: ["deleteRow":indexPath])
            }
            let menu = UIMenu(title: "", children: [moveToDone, delete])
            
            return menu
        }
        return configuration
    }
}
