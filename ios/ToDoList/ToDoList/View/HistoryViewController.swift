//
//  HistoryViewController.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/17.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class HistoryViewController: UIViewController {
    
    var logData: [LogDetailInfo]? = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        APIClient.apiClient.requestHistory()
        
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (allocateData),
                                               name: .completeHistoryLoad,
                                               object: nil)
        
    }
    
    @objc private func allocateData(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: [LogDetailInfo]] else { return }
        self.logData = notificationInfo["historyData"]
    }
    
}
