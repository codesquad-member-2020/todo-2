//
//  HistoryViewController.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/17.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class HistoryViewController: UIViewController {
    @IBOutlet weak var historyTableView: UITableView!
    
    let dataSource = HistoryViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        APIClient.apiClient.requestHistory()
        
        historyTableView.dataSource = dataSource
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (allocateData),
                                               name: .completeHistoryLoad,
                                               object: nil)
        
    }
    
    @objc private func allocateData(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: LogData.LogDetailData] else { return }
        dataSource.logData = notificationInfo["historyData"]
        dataSource.logData?.logs.reverse()
        historyTableView.reloadData()
    }
    
}
