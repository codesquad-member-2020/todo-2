//
//  ViewController.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/06.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    var toDoViewController: TodoListViewController?
    var progressViewController: TodoListViewController?
    var completeViewContrller: TodoListViewController?
    
    let apiClient = APIClient()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        apiClient.requestAllCategoryCard()
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (distributeData),
                                               name: .completeLoad,
                                               object: nil)
        setTitle()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "To Do" {
            toDoViewController = segue.destination as? TodoListViewController
        } else if segue.identifier == "Doing" {
            progressViewController = segue.destination as? TodoListViewController
        } else {
            completeViewContrller = segue.destination as? TodoListViewController
        }
    }
    
    private func setTitle() {
        toDoViewController?.columnName.text = "To Do"
        progressViewController?.columnName.text = "Doing"
        completeViewContrller?.columnName.text = "Done"
    }
    
    @objc func distributeData(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: CardData] else { return }
        let model = notificationInfo["responseData"]
        print(model)
    }
    
}

