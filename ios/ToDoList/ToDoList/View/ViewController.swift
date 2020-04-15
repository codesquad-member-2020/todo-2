//
//  ViewController.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/06.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var toDoViewController: TodoListViewController!
    var progressViewController: TodoListViewController!
    var completeViewContrller: TodoListViewController!
    
    let apiClient = APIClient()
    var cardData: CardData.ProjectData!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        apiClient.requestAllCategoryCard()
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (distributeData),
                                               name: .completeLoad,
                                               object: nil)
        
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (movingCard),
                                               name: .moveCardToDone,
                                               object: nil)
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
    
    @objc private func distributeData(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: CardData.ProjectData] else { return }
        self.cardData = notificationInfo["responseData"]
        
        setModelAtViewController(targetViewController: toDoViewController, categoryData: cardData.categories[0])
        setModelAtViewController(targetViewController: progressViewController, categoryData: cardData.categories[1])
        setModelAtViewController(targetViewController: completeViewContrller, categoryData: cardData.categories[2])
    }
    
    @objc private func movingCard(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: CardDetailData] else { return }
        let card = notificationInfo["moveCard"]
        
        completeViewContrller.tableViewDataSource.cardList?.append(card!)
        completeViewContrller.taskCardTableView.reloadData()
    }
    
    private func setModelAtViewController(targetViewController: TodoListViewController, categoryData: CategoryData) {
        targetViewController.tableViewDataSource.cardList = categoryData.cards
        targetViewController.taskCardTableView.reloadData()
        targetViewController.columnName.text = categoryData.title
    }
    
}

