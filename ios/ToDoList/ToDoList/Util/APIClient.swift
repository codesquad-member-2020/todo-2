//
//  APIClient.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/10.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

class APIClient {
    let defaultSession = URLSession(configuration: .default)
    var dataTask: URLSessionDataTask?
    
    func requestAllCategoryCard() {
        guard let url = URL(string: "http://15.164.28.20:8080/projects/1") else { return }
        let request = URLRequest(url: url)
        
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            guard let data = data, let responseData = try? JSONDecoder().decode(CardData.self, from: data) else { print("responseDataError"); return; }
            
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: .completeLoad, object: nil, userInfo: ["responseData":responseData.data.project])
            }
        }
        dataTask!.resume()
    }
    
    func requestAddNewCard(categoryId: Int, title: String, content: String) {
        guard let url = URL(string: "http://15.164.28.20:8080/mock/projects/1/categories/\(categoryId)/cards") else { return }
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
        let body = "content=\(content)&title=\(title)".data(using:String.Encoding.utf8, allowLossyConversion: false)
        request.httpBody = body
    }
}
