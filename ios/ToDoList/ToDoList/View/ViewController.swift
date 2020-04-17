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
    
    var cardData: CardData.ProjectData?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        APIClient.apiClient.requestAllCategoryCard()
        
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (distributeData),
                                               name: .completeLoad,
                                               object: nil)
        
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (movingCard),
                                               name: .moveCardToDone,
                                               object: nil)
        
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (reloadAllData),
                                               name: .reloadData,
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
        print(cardData)
        
        setModelAtViewController(targetViewController: toDoViewController, categoryData: cardData, index: 0)
        setModelAtViewController(targetViewController: progressViewController, categoryData: cardData, index: 1)
        setModelAtViewController(targetViewController: completeViewContrller, categoryData: cardData, index: 2)
    }
    
    @objc private func movingCard(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: CardDetailData] else { return }
        let card = notificationInfo["moveCard"]
        
        var doneCardList = completeViewContrller.tableViewDataSource.cardList?.cards
        var lastIndex = 0
        
        if doneCardList?.count != 0 {
            lastIndex = doneCardList![doneCardList!.endIndex-1].id
        }
        
        doneCardList?.append(card!)
        APIClient.apiClient.requestMoveCardToDone(destCategoryId: completeViewContrller.columnId!, cardId: card!.id, previousCardId: lastIndex)
    }
    
    @objc private func reloadAllData() {
        APIClient.apiClient.requestAllCategoryCard()
    }
    
    private func setModelAtViewController(targetViewController: TodoListViewController, categoryData: CardData.ProjectData?, index: Int) {
        targetViewController.tableViewDataSource.cardList = categoryData?.categories[index]
        targetViewController.columnId = categoryData?.categories[index].id
        targetViewController.taskCardTableView.reloadData()
        targetViewController.columnName.text = categoryData?.categories[index].title
    }
    
}

