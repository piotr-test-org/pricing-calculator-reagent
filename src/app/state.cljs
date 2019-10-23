(ns app.state
  (:require [reagent.core :as r]))

(defonce app-state (r/atom {:currency :eur
                            :products {1 {:id 1
                                          :instance :running_storage_huge
                                          :license :license_win
                                          :volume_data 100}
                                       2 {:id 2
                                          :instance :running_gpu_medium
                                          :license :none
                                          :volume_data 0}
                                       3 {:id 3
                                          :instance :running_medium
                                          :license :none
                                          :volume_data 200}}}))


